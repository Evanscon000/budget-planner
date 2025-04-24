import { describe, test, expect, vi } from 'vitest';
import { render, screen, waitFor } from '@testing-library/react';
import BudgetList from '../components/BudgetList';

// 🛠️ optional reusable render helper to prevent act warnings
const doRender = async (ui: React.ReactElement) => waitFor(() => render(ui));

describe('BudgetList', () => {
    test('renders a list of budget items', async () => {
        const mockData = [
            { id: 1, description: 'Rent', amount: 1200, category: 'Housing', date: '2024-04-25' },
            { id: 2, description: 'Groceries', amount: 150, category: 'Food', date: '2024-04-20' },
        ];

        globalThis.fetch = vi.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve(mockData),
            } as Response)
        ) as typeof fetch;

        await doRender(<BudgetList />);

        for (const item of mockData) {
            expect(await screen.findByText(item.description)).toBeInTheDocument();
            expect(screen.getByText((text) => text.includes(`$${item.amount}`))).toBeInTheDocument();
        }

    });
});
