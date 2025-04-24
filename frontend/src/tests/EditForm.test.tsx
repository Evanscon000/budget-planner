import { describe, test, expect, vi } from 'vitest';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import EditForm from '../components/EditForm';

const doRender = async (ui: React.ReactElement) => waitFor(() => render(ui));

describe('EditForm', () => {
    test('renders the form with pre-filled values and updates on submit', async () => {
        const mockItem = {
            id: 1,
            description: 'Gas',
            amount: 40,
            category: 'Transport',
            date: '2024-04-26',
        };

        globalThis.fetch = vi.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({}),
            } as Response)
        ) as typeof fetch;

        const onUpdate = vi.fn();

        await doRender(<EditForm item={mockItem} onUpdate={onUpdate} />);

        // Confirm the form shows existing data
        expect(screen.getByDisplayValue('Gas')).toBeInTheDocument();
        expect(screen.getByDisplayValue('40')).toBeInTheDocument();
        expect(screen.getByDisplayValue('Transport')).toBeInTheDocument();
        expect(screen.getByDisplayValue('2024-04-26')).toBeInTheDocument();

        // Change the amount and submit
        await userEvent.clear(screen.getByLabelText(/amount/i));
        await userEvent.type(screen.getByLabelText(/amount/i), '50');
        await userEvent.click(screen.getByRole('button', { name: /submit/i }));

        await waitFor(() =>
            expect(onUpdate).toHaveBeenCalledWith({
                id: 1,
                description: 'Gas',
                amount: 50,
                category: 'Transport',
                date: '2024-04-26',
            })
        );
    });
});
